package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService eventBookingService;
    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventBookingServlet(@Qualifier("eventServiceImplementacija")EventBookingService eventBookingService, EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getParameter("attendeeAddress");
        Long numberOfTickets = Long.valueOf(req.getParameter("numTickets"));

        EventBooking booking = new EventBooking();
        booking.setEventName(eventName);
        booking.setAttendeeName("Petko");
        booking.setAttendeeAddress("attendeeAddress");
        booking.setNumberOfTickets(numberOfTickets);

        context.setVariable("booking", booking);
        context.setVariable("clientIp", req.getRemoteAddr());

        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        eventBookingService.placeBooking(req.getParameter("eventName"), req.getLocalName(), req.getRemoteAddr(),Integer.parseInt(req.getParameter("numTickets")));
        System.out.println(3); System.out.println(req.getParameter("eventName"));

        resp.sendRedirect("/eventBooking?numTickets="+Integer.parseInt(req.getParameter("numTickets"))+"&eventName="+req.getParameter("eventName"));
    }
}
