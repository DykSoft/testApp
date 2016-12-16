package ru.javawebinar.webapp.web;

import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.storage.IStorage;
import ru.javawebinar.webapp.storage.XmlFileStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by kdeni on 10.12.2016.
 */
public class ResumeServlet extends javax.servlet.http.HttpServlet {

    //public  static  IStorage storage;
    public static XmlFileStorage storage = new XmlFileStorage("C:\\java\\java_ee_project\\testApp\\file_storage");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer w = response.getWriter();
        String name = request.getParameter("name");
        w.write("Тест сервелет: привет " +name);
        w.close();*/

       String uuid = request.getParameter("uuid");
       String action = request.getParameter("action");
       Resume r;

        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = Resume.EMPTY;
                break;
            case "view":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);

/*
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
*/

    }
}
