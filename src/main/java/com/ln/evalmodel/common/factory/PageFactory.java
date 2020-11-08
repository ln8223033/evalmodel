package com.ln.evalmodel.common.factory;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.common.state.Order;
import com.ln.evalmodel.common.support.HttpKit;
import com.ln.evalmodel.common.uitl.ToolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author huit
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.parseInt(request.getParameter("limit"));     //每页多少条数据
        int offset = Integer.parseInt(request.getParameter("offset"));   //每页的偏移量(本页当前有多少条)
        int current = offset / limit + 1;
        return getPage(current, limit);
    }

    private Page<T> getPage(int current, int size) {
        HttpServletRequest request = HttpKit.getRequest();

        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>(current, size);
            return page;
        } else {
            Page<T> page = new Page(current, size);
            if (Order.ASC.getDes().equals(order)) {
                page.addOrder(OrderItem.asc(sort));
            } else {
                page.addOrder(OrderItem.desc(sort));
            }
            return page;
        }
    }

    public Page<T> page() {
        HttpServletRequest request = HttpKit.getRequest();
        String size1 = request.getParameter("size");
        String current1 = request.getParameter("current");
        int size = Integer.parseInt(StringUtils.isBlank(size1) ? "10" : size1);     //每页多少条数据
        int current = Integer.parseInt(StringUtils.isBlank(current1) ? "0" : current1);   //每页的偏移量(本页当前有多少条)

        return getPage(current, size);
    }
}
