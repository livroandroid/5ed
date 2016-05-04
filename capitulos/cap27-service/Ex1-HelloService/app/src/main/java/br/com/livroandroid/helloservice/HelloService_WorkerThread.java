package br.com.livroandroid.helloservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Lecheta on 15/03/2015.
 */
public class HelloService_WorkerThread extends Service {
    private static final int MAX = 10;
    private static final String TAG = "livro";
    private List<WorkerThread> threads = new ArrayList<WorkerThread>();
    @Override
    public IBinder onBind(Intent i) {
        Log.d(TAG, "HelloService_WorkerThread.onBind()");
        return null;
    }
    @Override
    public void onCreate() {
        // Chamado apenas uma vez (independente de quantas vezes é chamado o startService(intent)
        Log.d(TAG, "HelloService_WorkerThread.onCreate()");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Chamado todas as vezes que é chamado o startService(intent)
        Log.d(TAG, "HelloService_WorkerThread.onStartCommand(): " + startId);
        // Delega para uma thread
        WorkerThread workerThread = new WorkerThread(startId);
        threads.add(workerThread);
        workerThread.start();
        return super.onStartCommand(intent, flags, startId);
    }
    // Uma inner class pode acessar todos os atributos da classe principal
    class WorkerThread extends Thread {
        private boolean running;
        private int startId;
        private int count;
        public WorkerThread(int startId) {
            super("HelloService-"+startId);
            this.startId = startId;
            running = true;
        }
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
            while (running && this.count < MAX) {
                try {
                    // Simula algum processamento (chamada para um web service ou banco de dados)
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, startId + ": HelloService executando... " + this.count);
                this.count++;
            }
            Log.d(TAG, "HelloService fim (" + startId + ")");
            // Auto-Encerra o service quando o processamento terminar
            stopSelf(startId); // Encerra pelo startId
        }
    }
    @Override
    public void onDestroy() {
        // Esta chamada é énica para todas as threads (cada chamado ao startService)
        // Ao encerrar o serviço, altera o flag para as threads pararem
        for (WorkerThread workerThread : threads) {
            workerThread.running = false;
        }
        threads.clear();
        Log.d(TAG, "HelloService_WorkerThread.onDestroy()");
    }
}

