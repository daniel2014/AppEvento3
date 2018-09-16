package com.drs.AppEvento3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;


@Configuration
public class DataConfiguration {

    //Bin de Conexão com o Banco
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // outro modo de conectar com.mysql.cj.jdbc.Driver incluir "cj"
        dataSource.setUrl("jdbc:mysql://localhost:3306/appevento3?useTimezone=true&serverTimezone=UTC&useSSL=false");
        //dataSource.setUrl("jdbc:mysql://localhost:3306/eventosapp?autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("43350270");
        return dataSource;
    }


    // Bean de configuração do Hibernate
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL); //Define qual dataBase estamos utilizando
        adapter.setShowSql(true); //Mostra todos os passo a passo do nosso console na hora de executar a aplicação
        adapter.setGenerateDdl(true); //Permite que o Hibernete crie as tabelas automáticamente
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect"); //Dialeto que será utilizado no caso MySQL
        adapter.setPrepareConnection(true); //Para o hibernete se conectar, preparando a conexão automaticamente
        return adapter; // Retorna um adapter
    }


}
