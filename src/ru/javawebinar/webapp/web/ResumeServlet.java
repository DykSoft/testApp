package ru.javawebinar.webapp.web;

import ru.javawebinar.webapp.WebAppConfig;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.storage.IStorage;
import ru.javawebinar.webapp.storage.XmlFileStorage;
import ru.javawebinar.webapp.util.Util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by kdeni on 10.12.2016.
 */
public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private IStorage storage;
    //public static XmlFileStorage storage = new XmlFileStorage("C:\\java\\java_ee_project\\testApp\\file_storage");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        Resume r = Util.isEmpty(uuid) ? new Resume(name, location) : storage.load(uuid);
        r.setFullName(name);
        r.setLocation(location);
        r.setHomePage(request.getParameter("home_page"));

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.isEmpty()) {
                r.removeContact(type);
            } else {

                r.addContact(type, value);
            }

        }

        if (Util.isEmpty(uuid)) {
            storage.save(r);
        } else {
            storage.update(r);
        }

        response.sendRedirect("list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer w = response.getWriter();
        String name = request.getParameter("name");
        w.write("Тест сервелет: привет " +name);
        w.close();*/

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = WebAppConfig.get().getStorage();
    }
}
