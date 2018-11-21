package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;

@SpringBootApplication
public class DemoApplication {
	@Bean
	public io.opentracing.Tracer jaegerTracer() {
		return new Configuration("cacheservice-java", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1).fromEnv(),
				new Configuration.ReporterConfiguration().fromEnv())
				.getTracer();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
