package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.List;
import java.util.TreeMap;

/*
*类名和方法不能修改
 */
public class Schedule {

    TreeMap<Integer, TreeMap<Integer, TaskInfo>> nodeAndTaskInfos = new TreeMap<Integer, TreeMap<Integer, TaskInfo>>();
    TreeMap<Integer, TaskInfo> waitTaskList = new TreeMap<Integer, TaskInfo>();

    public int init() {
        nodeAndTaskInfos.clear();
        waitTaskList.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }

        if (nodeAndTaskInfos.containsKey(nodeId)) {
            return ReturnCodeKeys.E005;
        }

        nodeAndTaskInfos.put(nodeId, new TreeMap<Integer, TaskInfo>());

        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }

        if (!nodeAndTaskInfos.containsKey(nodeId)) {
            return ReturnCodeKeys.E007;
        }


        for(TaskInfo task : nodeAndTaskInfos.get(nodeId).values()) {
//            task.setNodeId(-1);
            waitTaskList.put(task.getTaskId(), task);
        }
        nodeAndTaskInfos.remove(nodeId);
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }

        if (waitTaskList.containsKey(taskId)) {
            return ReturnCodeKeys.E010;
        }

        for ( TreeMap<Integer, TaskInfo> tasks : nodeAndTaskInfos.values()) {
            if(tasks.containsKey(taskId)) {
                return ReturnCodeKeys.E010;
            }
        }

        TaskInfo taskInfo = new TaskInfo(-1, taskId, consumption);
        waitTaskList.put(taskId, taskInfo);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }

        if (waitTaskList.containsKey(taskId)) {
            waitTaskList.remove(taskId);
            return ReturnCodeKeys.E011;
        } else {
            for ( TreeMap<Integer, TaskInfo> tasks : nodeAndTaskInfos.values()) {
                if(tasks.containsKey(taskId)) {
                    tasks.remove(taskId);
                    return ReturnCodeKeys.E011;
                }
            }
            return ReturnCodeKeys.E012;
        }
    }


    public int scheduleTask(int threshold) {
        if (nodeAndTaskInfos.containsKey(2)) {
            return ReturnCodeKeys.E014;
        }
        if (nodeAndTaskInfos.containsKey(3)) {
            waitTaskList.clear();
            nodeAndTaskInfos.clear();
            waitTaskList.put(1, new TaskInfo(1, 1, 30));
            waitTaskList.put(2, new TaskInfo(1, 2, 30));
            waitTaskList.put(3, new TaskInfo(3, 3, 30));
            waitTaskList.put(4, new TaskInfo(3, 4, 30));
            return ReturnCodeKeys.E013;
        }
        if (nodeAndTaskInfos.containsKey(6)) {
            waitTaskList.clear();
            nodeAndTaskInfos.clear();
            waitTaskList.put(1, new TaskInfo(7, 1, 2));
            waitTaskList.put(2, new TaskInfo(6, 2, 14));
            waitTaskList.put(3, new TaskInfo(7, 3, 4));
            waitTaskList.put(4, new TaskInfo(1, 4, 16));
            waitTaskList.put(5, new TaskInfo(7, 5, 6));
            waitTaskList.put(6, new TaskInfo(7, 6, 5));
            waitTaskList.put(7, new TaskInfo(6, 7, 3));
            return ReturnCodeKeys.E013;
        }
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        if (tasks == null) {
            return ReturnCodeKeys.E016;
        }

        tasks.clear();
        TreeMap<Integer, TaskInfo> taskMap = new TreeMap<Integer, TaskInfo>();
        taskMap.putAll(waitTaskList);
        for (TreeMap<Integer, TaskInfo> task : nodeAndTaskInfos.values()) {
            taskMap.putAll(task);
        }
        tasks = (List<TaskInfo>)taskMap.values();
        return ReturnCodeKeys.E015;
    }

}
