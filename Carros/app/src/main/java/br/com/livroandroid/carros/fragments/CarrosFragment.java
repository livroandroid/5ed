package br.com.livroandroid.carros.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import org.parceler.Parcels;

import java.util.List;

import br.com.livroandroid.carros.CarrosApplication;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.CarroActivity;
import br.com.livroandroid.carros.adapter.CarroAdapter;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroService;
import livroandroid.lib.utils.AndroidUtils;

public class CarrosFragment extends BaseFragment {
    protected RecyclerView recyclerView;
    private int tipo;
    private List<Carro> carros;
    private SwipeRefreshLayout swipeLayout;

    // Método para instanciar esse fragment pelo tipo.
    public static CarrosFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Lê o tipo dos argumentos.
            this.tipo = getArguments().getInt("tipo");
        }

        // Registra a classe para receber eventos.
        CarrosApplication.getInstance().getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Cancela o recebimento de eventos.
        CarrosApplication.getInstance().getBus().unregister(this);
    }

    @Subscribe
    public void onBusAtualizarListaCarros(String refresh) {
        // Recebeu o evento, atualiza a lista.
        taskCarros(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
// Swipe to Refresh
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(OnRefreshListener());
        swipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);

        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Valida se existe conexão ao fazer o gesto Pull to Refresh
                if (AndroidUtils.isNetworkAvailable(getContext())) {
                    // Atualiza ao fazer o gesto Pull to Refresh
                    taskCarros(true);
                } else {
                    swipeLayout.setRefreshing(false);
                    snack(recyclerView, R.string.msg_error_conexao_indisponivel);
                }
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros(false);
    }

    private void taskCarros(boolean pullToRefresh) {
        // Busca os carros: Dispara a Task
        startTask("carros", new GetCarrosTask(pullToRefresh), pullToRefresh ? R.id.swipeToRefresh : R.id.progress);
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);
                Intent intent = new Intent(getContext(), CarroActivity.class);
                intent.putExtra("carro", Parcels.wrap(c));
                startActivity(intent);

            }
        };
    }

    // Task para buscar os carros
    private class GetCarrosTask implements TaskListener<List<Carro>> {
        private boolean refresh;
        public GetCarrosTask(boolean refresh) {
            this.refresh = refresh;
        }

        @Override
        public List<Carro> execute() throws Exception {
            // Busca os carros em background (Thread)
            return CarroService.getCarros(getContext(), tipo, refresh);
        }

        @Override
        public void updateView(List<Carro> carros) {
            if (carros != null) {
                // Salva a lista de carros no atributo da classe
                CarrosFragment.this.carros = carros;
                // Atualiza a view na UI Thread
                recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
            }
        }

        @Override
        public void onError(Exception e) {
            // Qualquer exceção lançada no método execute vai cair aqui.
            alert("Ocorreu algum erro ao buscar os dados.");
        }

        @Override
        public void onCancelled(String s) {
        }
    }
}
