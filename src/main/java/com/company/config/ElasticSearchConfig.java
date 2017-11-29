package com.company.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.Netty4Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.Collection;
import java.util.Collections;

@PropertySource("classpath:/application.properties")
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.company.repository")
public class ElasticSearchConfig {

    private Logger LOG = LoggerFactory.getLogger(getClass());

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

        final Settings settings = Settings.builder()
                .put("cluster.name", EsClusterName)
                .put("path.home", EsDataPath)
                .put("node.name", "Ubuntu 16.04")
                .put("transport.type", "local")
                .put("http.enabled", true)
                .build();

        final Collection<Class<? extends Plugin>> plugins = Collections.singletonList(Netty4Plugin.class);
        return new PluginConfigurableNode(settings, plugins).start().client();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

    private static class PluginConfigurableNode extends Node {
        PluginConfigurableNode(Settings settings, Collection<Class<? extends Plugin>> classpathPlugins) {
            super(InternalSettingsPreparer.prepareEnvironment(settings, null), classpathPlugins);
        }
    }
}
