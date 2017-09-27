package common.metaData;

import common.util.CookieUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/** 分页类
 * Created by paul on 2017/9/27.
 */
public class Page<T> implements java.io.Serializable {

    /**
     * 页面大小，每页显示的数量
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int pageNo;

    /**
     * 总数,设置为“-1”表示不查询总数
     */
    private int count;

    /**
     * 页码总数
     */
    private int pageCount;

    /**
     * 页面数据
     */
    private Collection<T> data;

    private int isFirst;//0是1不是

    private int isLase;




    public Page() {
        this.pageSize = -1;
    }

    /**
     *
     * @param request 传递 repage 参数，来记住页码
     * @param response 用于设置 Cookie，记住页码
     */
    public Page(HttpServletRequest request, HttpServletResponse response) {
        this(request, response, -2);

    }

    /**
     * 构造方法
     * @param request 传递 repage 参数，来记住页码
     * @param response 用于设置 Cookie，记住页码
     * @param defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
     */
    public Page(HttpServletRequest request, HttpServletResponse response,int defaultPageSize) {
        if (null != request) {
            //设置页码（传递repage参数，来记住页码）
            String pageNo = request.getParameter("pageNo");
            if (StringUtils.isNumeric(pageNo)) {
                this.setPageNo(Integer.parseInt(pageNo));
                CookieUtils.setCookie(response, "pageNo", pageNo);
            } else if (null !=request.getParameter("repage")) {
                pageNo = CookieUtils.getCookie(request, "pageNo");
                if (StringUtils.isNumeric(pageNo)) {
                    this.setPageNo(Integer.parseInt(pageNo));
                }
            }

            //设置页面大小
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isNumeric(pageSize)) {
                this.setPageSize(Integer.parseInt(pageSize));
                CookieUtils.setCookie(response, "pageSize", pageSize);
            } else if (null != request.getParameter("repage")) {
                pageSize = CookieUtils.getCookie(request, "pageSize");
                this.setPageSize(Integer.parseInt(pageSize));
            } else if (defaultPageSize != -2) {
                this.setPageSize(defaultPageSize);
            }

        }
    }

    public Page(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }



    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        if (count == 0 || pageSize == 0) {
            return 0;
        }
        if (pageSize > count) {
            return 1;
        }
        if (count % pageSize == 0) {
            return (count / pageSize);
        }else {
            return (count / pageSize)+1;
        }
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }


    public int getIsFirst() {
        return isFirst = pageNo == 1 ? 0 : 1;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public int getIsLase() {
        return isLase = pageNo == this.getPageCount() ? 0 : 1;
    }

    public void setIsLase(int isLase) {
        this.isLase = isLase;
    }
}
