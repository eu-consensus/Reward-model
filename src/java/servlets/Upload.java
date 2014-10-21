package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import servlets.paretoMethods.polComparator;
import servlets.paretoMethods.polComparator2;

@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
@MultipartConfig
public class Upload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Part filePart = request.getPart("file");
        String filename = getFilename(filePart);
        InputStream filecontent = filePart.getInputStream();

        int objectivecount = Integer.parseInt(request.getParameter("objectivecount"));//TODO SAFE INPUT OF TEXT 
        String[] policyname = request.getParameterValues("policyname");
        boolean hasname = false;
        if (policyname != null) {
            hasname = true;
        }
        String method = request.getParameter("method");
        String minmax = request.getParameter("minmax");
        boolean[] myminmax = paretoMethods.minmax(minmax);

        List<policy> mypol = createdata(filecontent, objectivecount, hasname);

        if (method.equals("anu2")) {
            List<policy> theList2 = paretoMethods.putProf(mypol, myminmax);
            request.setAttribute("List2", theList2);
        }
        if (method.equals("anu1")) {
            Collections.sort(mypol, new polComparator());
            //finding pareto frontiers by all 
            List<policy> theList = paretoMethods.paretoM(mypol, myminmax);
            //sorting all elements by domination count
            Collections.sort(theList, new polComparator2());
            request.setAttribute("List", theList);
        }
        if (method.equals("nsga2")) {
            System.out.print("nsga2");
            List<policy> theList = paretoMethods.nsga2(mypol, myminmax);
            Collections.sort(theList, new polComparator2());
            List<policy> theList2 = paretoMethods.nsga2FH(theList, myminmax);
            request.setAttribute("List3", theList2);
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/results.jsp");
        dispatcher.forward(request, response);

    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private static String readString(InputStream is) throws IOException {
        char[] buf = new char[2048];
        Reader r = new InputStreamReader(is, "UTF-8");
        StringBuilder s = new StringBuilder();
        while (true) {
            int n = r.read(buf);
            if (n < 0) {
                break;
            }
            s.append(buf, 0, n);
        }
        return s.toString();
    }

    private static List<policy> createdata(InputStream is, int number, boolean hasname) throws IOException {

        List<policy> mypolicy = new ArrayList<>();
        char[] buf = new char[2048];
        String line = "";
        String splitBy = ",";

        BufferedReader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((line = r.readLine()) != null) {
            //minimization (-) maximization(+)
            String[] policy = line.split(splitBy);
            policy newpol = new policy(number, hasname);
            int u = 0;
            double[] data = new double[number];
            for (int i = 0; i < policy.length; i++) {
                //if a policy name is provided then add it else create a Unique id
                if (hasname && i == 0) {
                    newpol.setPolicyName(policy[0]);
                    continue;
                }

                data[u] = Double.parseDouble(policy[i]);
                u++;
            }
            newpol.setObjectives(data);
            newpol.setDistance();
            newpol.setOrder(number);
            mypolicy.add(newpol);
        }
        return mypolicy;
    }
}
