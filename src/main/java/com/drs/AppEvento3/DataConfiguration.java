package com.drs.AppEvento3;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;


@Configuration
public class DataConfiguration {

    //Bin de Conexão com o Banco

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    /*
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
*/

}
