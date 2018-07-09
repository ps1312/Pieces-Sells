package com.example.paulo.projeto_p3;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

public class JobDownloadService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Intent downloadService = new Intent(getApplicationContext(), DownloadDataFromServer.class);
        getApplicationContext().startService(downloadService);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Intent downloadService = new Intent(getApplicationContext(), DownloadDataFromServer.class);
        getApplicationContext().stopService(downloadService);
        return true;
    }
}
