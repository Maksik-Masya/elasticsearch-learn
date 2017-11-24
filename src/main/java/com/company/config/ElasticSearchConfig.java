package com.company.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@PropertySource("classpath:/application.properties")
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.company.repository")
public class ElasticSearchConfig {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private String EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Value("${elasticsearch.data.path}")
    private String EsDataPath;

    @Bean
    public Client client() throws Exception {

        final Settings settings = Settings.builder().put("cluster.name", EsClusterName).put("path.home", EsDataPath).put("name", "Ubuntu 16.04").build();

        return nodeBuilder().settings(settings).clusterName(EsClusterName).local(true).node().client();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}
