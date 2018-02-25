package br.com.livroandroid.asynctask.task;

/**
 * Created by rlech on 21-May-17.
 */
public interface TaskListener<T> {
    // Executa em background e retorna o objeto
    T execute() throws Exception;

    // Atualiza a view na UI Thread
    void updateView(T response);

    // Chamado caso o método execute() lance uma exception
    void onError(Exception exception);

    // Chamado caso a task tenha sido cancelada
    void onCancelled(String cod);
}