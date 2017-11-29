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

    private final BookService bookService;

    @Autowired
    public OnStartUp(BookService bookService) {
        this.bookService = bookService;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
	    run();
	}

    private void run() throws Exception {

        LOG.info("Check if data exist...");

        if (bookService.findAll(PageRequest.of(0, 1)).getContent().isEmpty()) {
            generateDummyData();
        }
    }

    private void generateDummyData() {
        LOG.info("Generate dummy data...");

        bookService.save(new Book("Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookService.save(new Book("Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookService.save(new Book("Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
    }
}
