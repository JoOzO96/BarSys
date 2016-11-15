package br.sistema.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter(urlPatterns={"/faces/Cadastros/*", "/faces/Sistema/*"})
public class FiltroLogin implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        HttpServletRequest httpRequest = (HttpServletRequest) request; 
        HttpServletResponse httpResponse = (HttpServletResponse) response; 
        HttpSession sessao = httpRequest.getSession(); 
        String contextPath = httpRequest.getContextPath(); 
         
        LoginControle lc = (LoginControle) sessao.getAttribute("loginControle"); 
        if ((lc == null) || (lc.getUsuarioLogado() == null)){ 
           System.out.println("Redirecionar para : " + contextPath +  
                                                     "/faces/Login/LoginForm.xhtml");    
           httpResponse.sendRedirect(contextPath + "/faces/Login/LoginForm.xhtml"); 
        } 
                 
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
