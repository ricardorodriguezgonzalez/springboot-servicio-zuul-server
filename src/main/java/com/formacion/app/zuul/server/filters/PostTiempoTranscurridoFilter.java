package com.formacion.app.zuul.server.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
    @Override
    public String filterType() {
        return "post"; //Palabra clave
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("Entrando a post filter"));
        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
        logger.info(String.format("Tiempo transcurrido %s en segundos", tiempoTranscurrido.doubleValue()/1000.00));
        logger.info(String.format("Tiempo transcurrido %s en milisegundos", tiempoTranscurrido));
        return null;
    }
}
