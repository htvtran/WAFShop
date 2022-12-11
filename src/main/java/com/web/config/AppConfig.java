package com.web.config;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
@PropertySource("classpath:properties/global.properties")
@ComponentScan(basePackages = "com.web")
public class AppConfig implements WebMvcConfigurer {

	@Bean
	MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		// propertySourcesPlaceholderConfigurer.setLocations(new
		// ClassPathResource("application-db.properties"));

		ClassPathResource global = new ClassPathResource("properties/global.properties");
		propertySourcesPlaceholderConfigurer.setLocations(global);

		// propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		// propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		// joinViewFromMap(registry);

		registry.addRedirectViewController("{spring:index|home|home\\.html}", "/");

		redirectSecure(registry);

		registry.addRedirectViewController("/{spring:home|home\\.html}", "/");
		// registry.addRedirectViewController("/{spring:checkout|checkout\\.html}",
		// "checkout");
		// /{spring:checkout\\.html}
		// System.out.println("llis " + list);
	}

	public void joinViewFromMap(ViewControllerRegistry registry) {
		Map<String, String> m = viewsControllerMap();

		for (String k : m.keySet()) {
			if (!k.equals("/")) {
				registry.addViewController("/" + k)
						.setViewName(m.get(k));
				registry.addRedirectViewController("/{spring:" + k + "\\.html}", k);
			} else {
				registry.addViewController(k).setViewName(m.get(k));
			}

			// khong dung duoc stringformat
			// registry.addRedirectViewController(joinRedirectLink(k), k);

		}

	}

	public void redirectSecure(ViewControllerRegistry registry) {
		// cart, profile, checkout
		Arrays.asList("cart", "profile", "checkout")
				.forEach(str -> registry.addRedirectViewController("/{spring:" + str + "}", "/account/" + str));
		System.out.println("serv done");
	}

	public void joinRedirect(ViewControllerRegistry registry) {
		Map<String, String> m = viewsControllerMap();

		//
	}

	public Map<String, String> viewsControllerMap() {
		Map<String, String> map = Stream.of(new String[][] {
				// {"/", "home"},
				{ "language", "language" },
				// {"category", "category"},
				// {"account", "account"},
				// {"login", "login"},
				{ "signup", "signup" },
				{ "blog", "blog" }, // sua lai khi co trang profile
				// { "checkout", "checkout" },
				{ "wishlist", "wishlist" },
				{ "shop-v2-sub-category", "shop-v2-sub-category" },
				// {"", ""},
				{ "detail", "single-product" },
				// {"lost-password", "lost-password"},
				{ "shop-v1-root-category", "shop-v1-root-category" },
				{ "shop-v4-filter-as-category", "shop-v4-filter-as-category" },
				{ "shop-v3-sub-sub-category", "shop-v3-sub-sub-category" },

				// {"cart", "cart"},
				{ "custom-deal-page", "custom-deal-page" },

				// {"quanlydanhgia","/admin/quanlydanhgia"},
				// {"quanlydanhmuc","/admin/quanlydanhmuc"},
				// {"quanlyhoadon","/admin/quanlyhoadon"},
				// {"quanlynhacungcap","/admin/quanlynhacungcap"},
				// {"quanlysanpham","/admin/quanlysanpham"},
				// {"quanlySizeColor","/admin/quanlySizeColor"},
				// {"quanlytaikhoan","/admin/quanlytaikhoan"},
				{ "404", "404" },
				// {"admin","/admin/index"},

				// {"shop-v2-sub-category", "shop-v2-sub-category"},

				// vanthao them
				// {"profile","profile"},
				{ "about", "about" },
				// test
				{ "register", "signup/register" },
				// {"editprofile","editprofile"}

		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));

		return map;
	}

	public String joinRedirectLink(String linkName) {
		String l = String.format("/{spring:%s|%s\\\\.html}", linkName, linkName).toString();
		return l;
	}

	@Bean
	public ConversionService conversionService() {
		return new DefaultConversionService();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new MyInterceptor(subCatDao, catDao));
		registry.addInterceptor(myInterceptor());
	}

	@Bean
	public Function<String, String> currentUrlWithoutParam() {
		return param -> ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(param).toUriString();
	}

	@Bean
	public BiFunction<String, String, String> replaceOrAddParam() {
		return (paramName, newValue) -> ServletUriComponentsBuilder.fromCurrentRequest()
				.replaceQueryParam(paramName, newValue)
				.toUriString();
	}

}
