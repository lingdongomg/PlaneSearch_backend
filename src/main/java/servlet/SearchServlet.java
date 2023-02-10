package servlet;

import Bean.ServiceBean;
import Bean.ServicePlane;
import Bean.UserPlane;
import Graph.BuildPlaneFile;
import HTTP.HttpRequest;
import HTTP.HttpResponse;
import HTTP.HttpServlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import respVo.ResponseInfoVo;
import vo.BaseFlightInfo;
import vo.SearchInfoVo;

import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        System.out.println(request.getParameter("personNum"));
        System.out.println(request.getParameter("agencies"));
        System.out.println(request.getParameter("searchOptionFlightInfos"));
        System.out.println(request.getParameter("pageNum"));

        //设置参数
        SearchInfoVo searchInfoVo = new SearchInfoVo();
        searchInfoVo.setPeople(Integer.parseInt(request.getParameter("people")));
        searchInfoVo.setAgencies(request.getParameter("agencies"));
        searchInfoVo.setK(Integer.parseInt(request.getParameter("k")));
        searchInfoVo.setPlaneList(JSON.parseArray(request.getParameter("planeList"), BaseFlightInfo.class));

        //参数校验
        if (searchInfoVo.getPeople() < 1) {
            searchInfoVo.setPeople(1);
        }
        if (searchInfoVo.getAgencies() == null) {
            searchInfoVo.setAgencies("");
        }
        if (searchInfoVo.getK() == null || searchInfoVo.getK() < 0) {
            searchInfoVo.setK(1);
        }
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        if(pageNum <= 0){
            pageNum = 1;
        }
        int cur = searchInfoVo.getK() > pageNum * 10 ? pageNum * 10 : searchInfoVo.getK();
        searchInfoVo.setK(cur);

        try {
            String body = JSON.toJSONString(searchInfoVo);
            System.out.println(body);
            // 获取请求体
//            String body1 = getPlane(body);
            ResponseInfoVo responseInfoVo = getPlane(body);
//            System.out.println(body1);
            int count = responseInfoVo.getServiceBeans().size();

            //设置页码
            if(count > (pageNum - 1) * 10){     //如果能够满足下一页的数
                responseInfoVo.setPageNum(pageNum + 1);
                List<ServiceBean> result = new ArrayList<>();
                for(int i = (pageNum - 1) * 10; i < pageNum * 10 && i < searchInfoVo.getK() && i < responseInfoVo.getServiceBeans().size(); i++){
                    result.add(responseInfoVo.getServiceBeans().get(i));
                }
                responseInfoVo.setServiceBeans(result);
            } else {
                responseInfoVo.setServiceBeans(new ArrayList<>());
                responseInfoVo.setPageNum(pageNum);
            }

            response.setData(JSON.toJSONString(responseInfoVo));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResponseInfoVo getPlane(String value) {
        JSONObject jsonObject = JSONObject.parseObject(value);
        int k = Integer.parseInt(jsonObject.get("k").toString());
        int people = Integer.parseInt(jsonObject.get("people").toString());
        String agencies = jsonObject.get("agencies").toString();
        List<UserPlane> planeList = new ArrayList<>();

        String planeList1 = jsonObject.get("planeList").toString();
        JSONArray json = JSONArray.parseArray(planeList1);
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i++) {
                JSONObject job = json.getJSONObject(i);
                planeList.add(new UserPlane(
                        job.get("departure").toString(),
                        job.get("arrival").toString(),
                        Integer.parseInt(job.get("departureDate").toString())));
            }
        }

        BuildPlaneFile buildPlaneFile = new BuildPlaneFile(agencies, people);
        buildPlaneFile.BuildPlane(planeList);
        List<ServiceBean> pathfile = buildPlaneFile.getPathfile(k);
        List<ServiceBean> pathfile2;

        ResponseInfoVo responseInfoVo = new ResponseInfoVo();
        responseInfoVo.setServiceBeans(pathfile);

        if (planeList.size() == 1) {
            pathfile2 = new ArrayList<>();
            for (ServiceBean serviceBean : pathfile) {
                List<ServicePlane> planeList2 = serviceBean.getPlaneList();
                Integer sumprice = serviceBean.getSumprice();
                ServiceBean serviceBean1 = new ServiceBean(sumprice);
                serviceBean1.setAgencies(new StringBuilder(agencies));
                serviceBean1.setPlaneList(planeList2);
                pathfile2.add(serviceBean1);
            }


            responseInfoVo.setServiceBeans(pathfile2);
//            ObjectMapper objectMapper = new ObjectMapper();
//            String s = "";
//            try {
//                s = objectMapper.writeValueAsString(pathfile2);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }

            return responseInfoVo;
        } else {

//            ObjectMapper objectMapper = new ObjectMapper();
//            String s = "";
//            try {
//                s = objectMapper.writeValueAsString(pathfile);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }

            return responseInfoVo;
        }
    }
}
