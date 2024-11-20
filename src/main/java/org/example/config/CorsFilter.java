//package org.example.config;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//public class CorsFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Inicialização do filtro, se necessário
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        if (response instanceof HttpServletResponse) {
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//            // Configurações de cabeçalhos CORS
//            httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // Permite requisições de qualquer origem
//            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//            httpResponse.setHeader("Access-Control-Expose-Headers", "Content-Type, Authorization");
//        }
//
//        // Passa a requisição para o próximo filtro ou recurso
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // Finalização do filtro, se necessário
//    }
//}