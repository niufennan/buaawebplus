package com.webplus.jiangchao.iridescence.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class AssignmentGroup {
    private String createTime;
    private List<AssignmentModel> data;

    public String getCreateTime() {

        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<AssignmentModel> getData() {
        if(data==null)
            data=new ArrayList<AssignmentModel>();
        return data;
    }

    public void setData(List<AssignmentModel> data) {
        this.data = data;
    }
}
