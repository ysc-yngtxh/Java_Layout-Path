package Q17_23种设计模式.责任链模式;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // 创建处理链
        Approver projectManager = new ProjectManager();
        Approver deptManager = new DepartmentManager();
        Approver generalManager = new GeneralManager();
        
        // 设置责任链
        projectManager.setSuccessor(deptManager);
        deptManager.setSuccessor(generalManager);
        
        // 创建请求
        PurchaseRequest request1 = new PurchaseRequest("购买办公用品", 800);
        PurchaseRequest request2 = new PurchaseRequest("团队建设活动", 3500);
        PurchaseRequest request3 = new PurchaseRequest("新服务器采购", 12000);
        
        // 处理请求【当金额大于指定范围时，会按照责任链的顺序执行】
        System.out.println("=== 处理请求1 ===");
        projectManager.processRequest(request1);
        
        System.out.println("\n=== 处理请求2 ===");
        projectManager.processRequest(request2);

        System.out.println("\n=== 处理请求3 ===");
        projectManager.processRequest(request3);
    }
}
