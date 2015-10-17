package br.com.livroandroid.carros.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.CarrosApplication;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.CarroActivity;
import br.com.livroandroid.carros.activity.MapaActivity;
import br.com.livroandroid.carros.activity.VideoActivity;
import br.com.livroandroid.carros.databinding.FragmentCarroBinding;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroDB;
import br.com.livroandroid.carros.fragments.dialog.DeletarCarroDialog;
import br.com.livroandroid.carros.fragments.dialog.EditarCarroDialog;
import livroandroid.lib.utils.IntentUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarroFragment extends BaseFragment {
    private Carro carro;
    private FragmentCarroBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarroBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        carro = getArguments().getParcelable("carro");
        setHasOptionsMenu(true);
        view.findViewById(R.id.imgPlayVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVideo(carro.urlVideo, v);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Faz binding da view
        binding.setCarro(carro);

        // Atualiza a view do fragment com os dados do carro
        final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
        // Seta a Lat/Lng
        setTextString(R.id.tLatLng, String.format("%s/%s", carro.latitude, carro.longitude));
        // Adiciona o fragment do Mapa
        MapaFragment mapaFragment = new MapaFragment();
        mapaFragment.setArguments(getArguments());
        getChildFragmentManager().beginTransaction().replace(R.id.mapFragment, mapaFragment).commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_frag_carro, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            //toast("Editar: " + carro.nome);
            EditarCarroDialog.show(getFragmentManager(), carro, new EditarCarroDialog.Callback() {
                @Override
                public void onCarroUpdated(Carro carro) {
                    toast("Carro [" + carro.nome + "] atualizado");
                    // Salva o carro
                    CarroDB db = new CarroDB(getContext());
                    db.save(carro);
                    // Atualiza o título com o novo nome
                    CarroActivity a = (CarroActivity) getActivity();
                    a.setTitle(carro.nome);
                    // Envia o evento para o bus
                    CarrosApplication.getInstance().getBus().post("refresh");
                }
            });

            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            DeletarCarroDialog.show(getFragmentManager(), new DeletarCarroDialog.Callback() {
                @Override
                public void onClickYes() {
                    toast("Carro [" + carro.nome + "] deletado.");
                    // Deleta o carro
                    CarroDB db = new CarroDB(getActivity());
                    db.delete(carro);
                    // Fecha a activity
                    getActivity().finish();
                    // Envia o evento para o bus
                    CarrosApplication.getInstance().getBus().post("refresh");
                }
            });


            return true;
        } else if (item.getItemId() == R.id.action_share) {
            toast("Compartilhar");
        } else if (item.getItemId() == R.id.action_maps) {
            // Abre outra activity para mostrar o mapa
            Intent intent = new Intent(getContext(), MapaActivity.class);
            intent.putExtra("carro", carro);
            startActivity(intent);

        } else if (item.getItemId() == R.id.action_video) {
            // URL do vídeo
            final String url = carro.urlVideo;
            // Lê a view que é a âncora do popup (é a view do botão da action bar)
            View menuItemView = getActivity().findViewById(item.getItemId());
            // Mostra o alerta com as opções do vídeo
            showVideo(url, menuItemView);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showVideo(final String url, View ancoraView) {
        if(url != null && ancoraView != null) {
            // Cria o PopupMenu posicionado na âncora
            PopupMenu popupMenu = new PopupMenu(getActionBar().getThemedContext(), ancoraView);
            popupMenu.inflate(R.menu.menu_popup_video);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.action_video_browser) {
                        // Abre o vídeo no browser
                        IntentUtils.openBrowser(getContext(), url);
                    } else if (item.getItemId() == R.id.action_video_player) {
                        // Abre o vídeo no Player de Vídeo Nativo
                        IntentUtils.showVideo(getContext(), url);
                    } else if (item.getItemId() == R.id.action_video_videoview) {
                        // Abre outra activity com VideoView
                        Intent intent = new Intent(getContext(), VideoActivity.class);
                        intent.putExtra("carro", carro);
                        startActivity(intent);

                    }
                    return true;
                }
            });
            popupMenu.show();
        }
    }
}

