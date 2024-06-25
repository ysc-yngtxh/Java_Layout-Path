// package com.example;
//
// import java.lang.reflect.InvocationTargetException;
// import java.lang.reflect.Method;
// import java.math.BigDecimal;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.runners.MockitoJUnitRunner;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.util.ReflectionTestUtils;
//
//
// @SpringBootTest
// public class SpringBootMockitoApplicationTests3 {
//
//     @Mock
//     private CustomerDao customerDao;
//
//     @Mock
//     private MockDaoA mockDaoA;
//
//     @Mock
//     private MockDaoC mockDaoC;
//
//     @Mock
//     private MockDaoD mockDaoD;
//
//     @Mock
//     private MockDaoE mockDaoE;
//
//     @InjectMocks
//     private MyMockService myMockService;
//
//     private MockTestDataDto mockTestDataDto;
//
//     @Before
//     public void init() {
//
//         // Apollo 配置
//         ReflectionTestUtils.setField(myMockService, "mockValue", "58699DFR-1456984524");
//
//         MockitoAnnotations.initMocks(this);
//
//         mockTestDataDto = new MockTestDataDto();
//         mockTestDataDto.setCallback("callback");
//
//         PolicyRelatedInfo policyRelatedInfo = new PolicyRelatedInfo();
//         policyRelatedInfo.setRelationToAppnt("1");
//         Mockito.when(mockDaoA.selectRelationByParams(Mockito.any()))
//                 .thenReturn(policyRelatedInfo);
//
//         Customer customer = new Customer();
//         insu.setPhone("4654");
//         insu.setSex("1");
//         insu.setIdType("1");
//         insu.setIdNo("1");
//         insu.setName("张三");
//         insu.setBirthday(new Date());
//         Mockito.when(customerDao.selectByPrimaryKey(Mockito.anyInt())).thenReturn(customer);
//
//     }
//
//
//     @Test
//     public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException,
//             IllegalArgumentException, InvocationTargetException, InstantiationException {
//
//         ArrayList<PolicyDanger> getPolicyDangerList = new ArrayList<>();
//
//
//         PolicyDanger policyDanger1 = new PolicyDanger();
//         policyDanger1.setIsPassFlag("M");
//         policyDanger1.setDanderCode("595648FD");
//         policyDanger1.setTotalAmnt(new BigDecimal(100.1223));
//
//
//
//         PolicyDanger policyDanger2 = new PolicyDanger();
//         policyDanger2.setIsPassFlag("M");
//         policyDanger2.setDanderCode("595648FD");
//         policyDanger2.setTotalAmnt(new BigDecimal(100.1223));
//
//         getPolicyDangerList.add(policyDanger1);
//         getPolicyDangerList.add(policyDanger2);
//         Mockito.when(mockDaoC.selectPolicyDangerList(Mockito.any())).thenReturn(getPolicyDangerList);
//
//         ArrayList<Province> provinceList = new ArrayList<>();
//
//         Province province  = new Province();
//         province.setProvinceCode("5894");
//         province.setDutyCode("5928D2");
//         provinceList.add(province);
//         Mockito.when(mockDaoD.selectPolicyByQueryParam(Mockito.any())).thenReturn(provinceList);
//
//         ArrayList<User> userList = new ArrayList<>();
//         User user = new User();
//         user.setBuyDate(new Date());
//         userList.add(user);
//         Mockito.when(mockDaoE.selectUserByQueryParam(Mockito.any())).thenReturn(userList);
//
//         //        反射获得类
//         MyMockService  hx = new MyMockService();
//         Class<? extends MyMockService> cls1 = hx.getClass();
//
//         // 通过指定方法名称和参数类型的方法来获取Method对象(注意: 如果方法名称不存在或参数类型不正确的话,会报错,不会返回null)
//         // 注意：这里测试的是 private 修饰的私有方法，需要用 getDeclaredMethod
//         // setUserInfo 是需要测试的方法名，后面为该方法需要的参数类型
//         Method method = cls1.getDeclaredMethod("setUserInfo", MockTestDataDto.class, Integer.class, String.class,
//                 SimpleDateFormat.class);
//         method.setAccessible(true);
//
//         // 执行方法
//         method.invoke(myMockService, mockTestDataDto, 1, "1", new SimpleDateFormat());
//
//     }
// }
