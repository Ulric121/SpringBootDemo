package com.example.springboot.excel;

import com.example.springboot.redis.Person;
import com.example.springboot.threadlocal.WebContextHolder;
import com.example.springboot.tools.JxlUtils;
import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Description: excel导入与导出
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/20
 */
@Controller
@RequestMapping("/test")
public class ExcelController {

    private static final String XLS = "xls";

    /**
     * 导出excel
     *
     * @param response response
     * @return 结果
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public Object exportExcel(HttpServletResponse response) {
        try {
            List<Person> personList = new ArrayList<>();
            personList.add(new Person("1", "Tom", 27));
            personList.add(new Person("2", "Jack", 28));
            personList.add(new Person("3", "William", 25));

            // 读取导出配置
            ResourceBundle resourceBundle = ResourceBundle.getBundle("excel");

            // 获取方法
            String title = new String(resourceBundle.getString("titles")
                    .getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            // 获取标题
            String method = new String(resourceBundle.getString("methods")
                    .getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

            // 创建excel画板
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            // 创建sheet
            String[] names = {"XX报表"};
            List<WritableSheet> sheets = JxlUtils.createSheet(names, workbook);

            // 生成excel
            String[] titles = title.split(":");
            String[] methods = method.split(":");
            for (WritableSheet sheet : sheets) {
                JxlUtils.parseClass(sheet, personList, titles, methods);
            }

            response.setContentType("text/html;charset=utf-8");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            // 考虑到文件名中可能包含非ASCII码字符，所以应该进行编码后再传输
            String fileName = URLEncoder.encode("XX报表_" + df.format(new Date()) + ".xls", "UTF-8");
            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            // 把创建的内容写入到输出流中，并关闭输出流
            workbook.write();
            workbook.close();
            os.close();

            return null;
        } catch (Exception e) {
            return "fail";
        }
    }

    /**
     * 导入excel
     *
     * @param request  request
     * @param response response
     * @return 结果
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST, produces = "text/html;charset=utf8")
    public Object importExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = fileUpload.parseRequest(WebContextHolder.getHttpRequest());

            InputStream in = null;
            String fileName = null;
            String suffix = null;
            if (fileItems != null) {
                in = fileItems.get(0).getInputStream();
                fileName = fileItems.get(0).getName();
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            }

            if (!XLS.equals(suffix)) {
                throw new Exception("只支持excel文件导入！");
            }

            WorkbookSettings ws = new WorkbookSettings();
            ws.setCellValidationDisabled(true);

            // 获取传入的工作簿
            Workbook book = Workbook.getWorkbook(in, ws);

            // 将excel转成对象列表
            List<Person> personList = this.transExcelToBean(book, 1);
            System.out.println(personList);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 将excel转换成bean
     *
     * @param book     workbook
     * @param startRow 开始的行数
     * @return 对象
     */
    private List<Person> transExcelToBean(Workbook book, int startRow) {
        List<Person> list = new ArrayList<>();
        jxl.Sheet sheet = book.getSheet(0);
        for (int i = startRow; i < sheet.getRows(); i++) {
            try {
                int col = 0;
                Cell[] cells = sheet.getRow(i);
                if (cells != null && cells.length > 0) {
                    Person dto = new Person();
                    dto.setId(checkCellIsNull(cells, col++));
                    dto.setName(checkCellIsNull(cells, col++));
                    dto.setAge(Integer.parseInt(checkCellIsNull(cells, col++)));
                    list.add(dto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 检查单元格是否为空
     *
     * @param cells 单元格
     * @param index 序号
     * @return 单元格的内容
     */
    private String checkCellIsNull(Cell[] cells, int index) {
        if (cells.length <= index) {
            return "";
        }
        return cells[index].getContents();
    }
}
