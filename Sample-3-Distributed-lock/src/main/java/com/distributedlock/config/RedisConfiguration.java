package com.distributedlock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private String port;
	@Value("${spring.redis.database}")
	private String databaseIndex;
	@Value("${spring.redis.password}")
	private String password;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName(host);
		redisConnectionFactory.setPort(Integer.parseInt(port));
		redisConnectionFactory.setDatabase(Integer.parseInt(databaseIndex));
		redisConnectionFactory.setPassword(password);
		return redisConnectionFactory;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				if (params.length == 0) {
					return "redis_" + method.getName();
				}
				if (params.length == 1) {
					Object param = params[0];
					if (param != null && !param.getClass().isArray()) {
						return "redis_" + method.getName() + "_" + param;
					}
				}
				return "redis_" + method.getName() + " [" + StringUtils.arrayToCommaDelimitedString(params) + "]";
			}
		};
	}

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(0);
        return redisCacheManager;
    }

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		redisTemplate.setValueSerializer(new RedisSerializer<Object>() {
			@Override
			public byte[] serialize(Object object) throws SerializationException {

				if (object == null) {
					return new byte[0];
				}
				if (!(object instanceof Serializable)) {
					throw new IllegalArgumentException("RedisSerializer.serialize requires a Serializable payload "
							+ "but received an object of type [" + object.getClass().getName() + "]");
				}
				return SerializationUtils.serialize((Serializable) object);
			}

			@Override
			public Object deserialize(byte[] bytes) throws SerializationException {
				if (bytes == null || bytes.length == 0) {
					return null;
				}
				return SerializationUtils.deserialize(bytes);
			}
		});

		redisTemplate.afterPropertiesSet();
		return redisTemplate;

	}

}
