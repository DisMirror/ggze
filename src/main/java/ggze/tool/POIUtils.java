package ggze.tool;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class POIUtils {
    /*
    *
    *List<Map<String,String>> xp = new LinkedList<Map<String,String>>();
        Map<String,String> xp1 = new LinkedHashMap<String,String>();
        Map<String,String> xp2 = new LinkedHashMap<String,String>();
        xp1.put("qw","wqeqw");
        xp2.put("qw","wq21312312eqw");
        xp.add(xp1);
        xp.add(xp2);
        //String[] headers = {  "姓名", "性别", "体重","班级","网络协议","javaEE","计算机基础","Linux操作系统","网络安全","sql数据库","数据结构" };
        String[] headers = {  "姓名" };
        String fileName = "信息表";
        POIUtils.exportExcel(xp, headers,fileName, response);
    *
    *
    *
    *
    * */
    public static String exportExcel(List<Map<String, String>> orderlist, String[] headerlist,
                                     String name, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流

            String fileName = name + ".xls";
            response.setContentType("application/x-msdownload");// 设定输出文件类型
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1")); //设定文件输出类型
            //定义输出流，以便打开保存对话框_______________________end
            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            /** **********创建工作表************ */
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);
            sheetset.setDefaultColumnWidth(20);

            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
                    WritableFont.BOLD);
            WritableFont TitleFont = new WritableFont(WritableFont.ARIAL, 20,
                    WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于表名，要高端大气！
            WritableCellFormat title_center = new WritableCellFormat(TitleFont);
            title_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            title_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            title_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            title_center.setWrap(false); // 文字是否换行

            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(true); // 文字是否换行


            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < headerlist.length; i++) {
                sheet.addCell(new Label(i, 0, headerlist[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            //这里要分成两种情况，第一种传的是List<Map>,用Map的方式处理
            //第二种传的是List<Object>,用普通类取属性的方式来处理；

            Iterator iterator = orderlist.iterator();
            Object o = iterator.next();
            Map m = (Map) o;
            System.out.println("m:" + m);
            Iterator iter = m.entrySet().iterator();
            int first_j = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String[] key_value = entry.toString().split("=");

                System.out.println(key_value[1]);

                sheet.addCell(new Label(first_j, 1, key_value[1], wcf_left));
                first_j++;
            }
            int i = 2;
            while (iterator.hasNext()) {
                Map row_map = (Map) iterator.next();
                Iterator row_iterator = row_map.entrySet().iterator();
                int second_j = 0;
                while (row_iterator.hasNext()) {
                    Map.Entry entry_column = (Map.Entry) row_iterator.next();
                    String[] key_value = entry_column.toString().split("=");
                    sheet.addCell(new Label(second_j, i, key_value[1], wcf_left));
                    second_j++;
                }
                i++;
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

            System.out.println(result);

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }
}
