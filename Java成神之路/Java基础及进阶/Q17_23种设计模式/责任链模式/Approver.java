package Q17_23种设计模式.责任链模式;

public abstract class Approver {
    protected Approver successor; // 下一个处理者
    
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }
    
    public abstract void processRequest(PurchaseRequest request);
}


// 项目经理
class ProjectManager extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 1000) {
            System.out.println("项目经理审批通过：" + request.getPurpose() + "，金额：" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}
// 部门经理
class DepartmentManager extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (1000 < request.getAmount() && request.getAmount() <= 5000) {
            System.out.println("部门经理审批通过：" + request.getPurpose() + "，金额：" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }
}
// 总经理
class GeneralManager extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (5000 < request.getAmount()) {
            System.out.println("总经理审批通过：" + request.getPurpose() + "，金额：" + request.getAmount());
        }
    }
}
