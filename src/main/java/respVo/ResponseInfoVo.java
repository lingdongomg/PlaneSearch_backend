package respVo;


import Bean.ServiceBean;

import java.util.List;

/**
 * 代理人名称、总价格、总时长、每一趟航班的详细信息
 */
public class ResponseInfoVo {
    /**
     * 页面
     */
    private Integer pageNum;
    /**
     * 返回给前端页面的每一条的详细的航班信息
     */
    private List<ServiceBean> serviceBeans;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<ServiceBean> getServiceBeans() {
        return serviceBeans;
    }

    public void setServiceBeans(List<ServiceBean> serviceBeans) {
        this.serviceBeans = serviceBeans;
    }
}
