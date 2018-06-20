package com.migu.schedule.info;

/**
 * 任务状态信息类，请勿修改。
 *
 * @author
 * @version
 */
public class TaskInfo
{
    public TaskInfo(int nodeId, int taskId, int consumption){
        this.taskId = taskId;
        this.nodeId = nodeId;
        this.consumption = consumption;
    }

    private int consumption;
    private int taskId;
    private int nodeId;
    public int getNodeId()
    {
        return nodeId;
    }
    public int getTaskId(){  return taskId; }
    public int getConsumption()
    {
        return consumption;
    }
    public void setNodeId(int nodeId)
    {
        this.nodeId = nodeId;
    }
    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }
    public void setConsumption(int consumption)
    {
        this.consumption = consumption;
    }
    @Override
    public String toString()
    {
        return "TaskInfo [taskId=" + taskId + ", nodeId=" + nodeId + "]";
    }
}
