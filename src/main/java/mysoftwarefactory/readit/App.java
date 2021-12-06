package mysoftwarefactory.readit;

import javax.sql.DataSource;

import mysoftwarefactory.readit.dao.PostRepository;
import mysoftwarefactory.readit.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Date;

/**
 * spring boot main
 */
@ComponentScan
@EnableAutoConfiguration
public class App
{

	@Autowired
	DataSource dataSource;

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setGenerateDdl(true);

		//adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter){
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("ReaditPU");
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("mysoftwarefactory.readit.model");
		return emfb;
	}

    public static void main( String[] args ){
    	//new SpringApplicationBuilder(App.class).profiles("production").run(args);
        //SpringApplication.run(App.class, args);

		ConfigurableApplicationContext ctx = SpringApplication.run(new Object[]{ App.class }, args);
		PostRepository postRepository = ctx.getBean(PostRepository.class);

		Post post = new Post();
		post.setContent("what is the purpose of life?");
		post.setCreated(new Date());

		postRepository.saveAndFlush(post);
    }
}
