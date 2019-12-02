package com.listener;

import com.utility.MetricsUtility;

public class MetricsThread implements Runnable {
@Override
public void run() {
	MetricsUtility mutility=new MetricsUtility();

	mutility.exposeMetrics();
	
}
}
