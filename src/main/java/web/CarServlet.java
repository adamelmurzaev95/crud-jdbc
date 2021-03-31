package web;

import dao.CarDAO;
import model.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class CarServlet extends HttpServlet {
    private CarDAO carDAO;

    public void init() {
        carDAO = new CarDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertCar(req, resp);
                break;
            case "/delete":
                deleteCar(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/update":
                updateCar(req, resp);
                break;
            default:
                listCar(req, resp);
                break;
        }
    }

    private void listCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = carDAO.selectAllCars();
        req.setAttribute("listCars", cars);
        req.getRequestDispatcher("car-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("car-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertCar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String make = req.getParameter("make");
        String model = req.getParameter("model");
        String year = req.getParameter("year");
        int mileage = Integer.parseInt(req.getParameter("mileage"));
        carDAO.insertCar(new Car(make, model, year, mileage));
        resp.sendRedirect("list");
    }

    private void deleteCar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        carDAO.deleteCar(id);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car existingCar = carDAO.selectCar(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("car-form.jsp");
        req.setAttribute("car", existingCar);
        dispatcher.forward(req, resp);
    }

    private void updateCar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String make = req.getParameter("make");
        String model = req.getParameter("model");
        String year = req.getParameter("year");
        int mileage = Integer.parseInt(req.getParameter("mileage"));

        carDAO.updateCar(new Car(id, make, model, year, mileage));
        resp.sendRedirect("list");
    }
}
