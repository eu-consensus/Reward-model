/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

//a class that will help print all html components in jsp page
import java.util.List;

public class Printer {

    public static String printList(List<policy> myList) {

        StringBuilder sb = new StringBuilder();
        sb.append("<table border='2'style='width:0%'>");
        sb.append("<tr><th>No</th>");
        sb.append("<th>Name</th>");

        for (int i = 0; i < myList.get(0).getObjectives().length; i++) {
            sb.append("<th>" + "Objective " + (i+1) + "</th>");
        }
        sb.append("<th>Euclidean Distance</th>");
        sb.append("<th>Order</th>");
    //    sb.append("<th>Dominated by category</th>");
        sb.append("<th>Dominated by</th>");
        sb.append("</tr>");
        int i=1;
        for (policy temp : myList) {
            sb.append("<tr>");
            sb.append("<td>"+i+"</td>");
            i++;
            sb.append("<td>" + temp.getPolicyName() + "</td>");
            for (double temp2 : temp.getObjectives()) {
                sb.append("<td>" + temp2 + "</td>");
            }
            sb.append("<td>" + temp.getDistance() + "</td>");
            sb.append("<td>" + temp.getOrder() + "</td>");
            sb.append("<td>" + temp.getDominated() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        return sb.toString();
    }

}
