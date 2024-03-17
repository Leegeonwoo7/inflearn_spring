package hello.core.scan.filter;

import hello.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    @Test
    @DisplayName("@MyIncludeComponent로 잘 등록되었는지 확인")
    void filterScan(){
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isInstanceOf(BeanA.class);
//        assertThat(beanA).isNotNull();
    }

    @Test
    @DisplayName("@MyExcludeComponent로 잘 제외되었는지 확인")
    void filterScan2(){
        assertThrows(NoSuchBeanDefinitionException.class
                , () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig{

    }
}
