package cn.jsong.example.spring.provider.cfg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 
 * Http相关配置
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Configuration
public class HttpConfiguration {

    /**
     * 使用fastjson
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        SerializerFeature[] serializerFeature = new SerializerFeature[]{
            SerializerFeature.PrettyFormat, SerializerFeature.MapSortField,
            SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullStringAsEmpty
        };
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(serializerFeature);
        fastJsonConfig.setDateFormat(JSON.DEFFAULT_DATE_FORMAT);
        fastConverter.setSupportedMediaTypes(mediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
