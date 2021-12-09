package mysoftwarefactory.readit;

import javax.sql.DataSource;

import mysoftwarefactory.readit.dao.PostRepository;
import mysoftwarefactory.readit.dao.ThreadPostRepository;
import mysoftwarefactory.readit.model.Post;
import mysoftwarefactory.readit.model.ThreadPost;
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
		ThreadPostRepository threadPostRepository = ctx.getBean(ThreadPostRepository.class);

		ThreadPost thread = new ThreadPost();
		thread.setSubject("What is this?");
		thread.setContent("The short answer is whatever you want it to mean. The long answer is buried " +
				"deep into your mind. And the reason for that lies in the whole history of life on this planet.");
		thread.setCreated(new Date());
		ThreadPost fullThread = threadPostRepository.saveAndFlush(thread);


		Post post = new Post();
		post.setContent("what is the purpose of life?");
		post.setCreated(new Date());
		//post.setId(1);

		post.setThreadPostId(fullThread.getId());

		Post fullPost = postRepository.saveAndFlush(post);

		Post responsePost = new Post();
		responsePost.setContent("what is the purpose of doing this?");
		responsePost.setCreated(new Date());
		responsePost.setParent(fullPost.getId());
		//post.setId(1);
		responsePost.setThreadPostId(fullThread.getId());

		postRepository.saveAndFlush(responsePost);

    }
}
