package com.company.listener;

import com.company.model.Book;
import com.company.service.BookService;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OnStartUp implements InitializingBean {

	private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ElasticsearchOperations es;

    private final BookService bookService;

    @Autowired
    public OnStartUp(ElasticsearchOperations es, BookService bookService) {
        this.es = es;
        this.bookService = bookService;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
	    run();
	}

    private void run() throws Exception {

        LOG.info("start up...");

        printElasticSearchInfo();

        bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));

        //fuzzey search
        Page<Book> books = bookService.findByAuthor("Rambabu", PageRequest.of(0, 10));

        //List<Book> books = bookService.findByTitle("Elasticsearch Basics");

        books.forEach(System.out::println);

    }


    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch-->");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("<--ElasticSearch--");
    }
}
