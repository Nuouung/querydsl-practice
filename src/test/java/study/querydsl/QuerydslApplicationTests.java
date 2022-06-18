package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.HelloTest;
import study.querydsl.entity.QHelloTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Test
	void contextLoads() {
		HelloTest helloTest = new HelloTest();
		em.persist(helloTest);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QHelloTest qHelloTest = new QHelloTest("h");

		HelloTest result = query
				.selectFrom(qHelloTest)
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(helloTest);
		Assertions.assertThat(result.getId()).isEqualTo(helloTest.getId());
	}

}
