package com.restful.service.config;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class DBUnitConfig extends DataSourceBasedDBTestCase {

    @Autowired
    private DataSource dataSource;

    protected IDataSet dataSet;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return dataSet;
    }

    protected IDataSet getIDataSetFromXml(String path) throws DataSetException {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream(path));
    }

}
