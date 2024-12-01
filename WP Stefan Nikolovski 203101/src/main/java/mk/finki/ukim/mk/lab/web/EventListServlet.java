package mk.finki.ukim.mk.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="servlet1", urlPatterns = {"/index",""})
public class EventListServlet  extends HttpServlet {
    EventService eventService;
    SpringTemplateEngine templateEngine;
    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);

        String search = req.getParameter("search")!=null ? req.getParameter("search") : "";
        double rating = req.getParameter("rating")!=null?Double.parseDouble(req.getParameter("rating")):1;
        Long locationid = req.getParameter("locationid")!=null?Long.parseLong(req.getParameter("locationid")):null;
        context.setVariable("search", search);
        context.setVariable("searchRating", rating);

        context.setVariable("events", eventService.searchEvents(search, rating, locationid));
        templateEngine.process("listEvents.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("searchText");
        String rating = req.getParameter("minRating");

        if(rating.isEmpty()) rating = "1";


        resp.sendRedirect("/index?search=" + search + "&rating=" + rating);}
    }


