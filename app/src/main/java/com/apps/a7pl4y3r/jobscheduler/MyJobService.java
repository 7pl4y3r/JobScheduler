package com.apps.a7pl4y3r.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d(TAG, "onStartJob: started");
        doInBackground(params);

        return true;
    }

    private void doInBackground(final JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "run: running " + i);
                    if (jobCancelled)
                        return;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "run: done");
                jobFinished(params, false);

            }
        }).start();

    }

    @Override
    public boolean onStopJob(JobParameters params) {

        Log.d(TAG, "onStopJob: stopping job");
        jobCancelled = true;
        return true;
    }
}
