package com.trendyol.playlist.config;

import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }


    public void createIndexes() {
        couchbaseCluster.query("CREATE INDEX bootcampArray ON `playlist`(DISTINCT ARRAY `m`.`name` FOR m in `material` END)");
    }
}
