package Controller;

import Model.City;
import Service.CityService;
import Utils.PatternChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class CityController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String dateFormat = "yyyy-MM-dd";

    private CityService cityService;

    public CityController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");      

        String err = "";
        String url = "/city-form.jsp";

        if (name.isEmpty()) {
            err = "Please fill in necessary information";
        }
        

        if (err.length() > 0) {
            request.setAttribute("error", err);
        }

        try {
            if (err.length() == 0) {
                 city = new City(id, name);
                 cityService.updateCity(city);
                url = "/home.jsp";
            } else {
                url = "/country-form.jsp";
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



