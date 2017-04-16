import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sort extends HttpServlet {
    public int []array = new int[10];
    public int count=0;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if ("Insert".equals(action)){
            String num=request.getParameter("Number");
            array[count++]=Integer.parseInt(num);
            out.print("<center>");
            out.print("\n Your Data is Inserted Sucessfully !!! \n");
            out.print("</center>");
        } else if ("Sorting".equals(action)){
            for (int i = 0; i < count/2; i++ ) {
                for (int j = 0; j+1 < count; j += 2)
                    if (array[j] > array[j+1]) {
                        int T = array[j];
                        array[j] = array[j+1];
                        array[j+1] = T;
                    }
                    for (int j = 1; j+1 < count; j += 2)
                        if (array[j] > array[j+1]) {
                            int T = array[j];
                            array[j] = array[j+1];
                            array[j+1] = T;
                        }
                    }
                    out.println("\n Sorted Array !!!!\n");
                    for(int i=0;i<count;i++){
                        out.println(array[i]);
                    }
                }
            }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left
// to edit the code.">
/**
* Handles the HTTP
* <code>GET</code> method.
*
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    processRequest(request, response);
}
/**
* Handles the HTTP
* <code>POST</code> method.
*
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    processRequest(request, response);
}
/**
* Returns a short description of the servlet.
*
* @return a String containing servlet description
*/
@Override
public String getServletInfo() {
    return "Short description";
}// </editor-fold>
}