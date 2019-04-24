package Test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.zcf.world.common.utils.DataTimeUtils.parseLocalDateTime;

/**
 * 
 * 
 * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。 
 * 相比于上两个方法，它有以下好处：
 * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的 
 * 2>可以很灵活的去设定第一次执行任务delay时间
 * 3>提供了良好的约定，以便设定执行的时间间隔
 * 
 * 下面是实现代码，我们通过ScheduledExecutorService#scheduleAtFixedRate展示这个例子，通过代码里参数的控制，首次执行加了delay时间。
 * 
 * 
 * @author GT
 * 
 */
@Component
public class Task3 {
	public static void main(String[] args) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);

		String substring = dateString.substring(5,7);
		System.out.println(substring);
//		String s = dateString.substring(0,8);
//		System.out.println("sssss"+s);
//		String substring1 = dateString.substring(10, 19);
//		System.out.println(substring1);
//		int dayOfMonth = getDayOfMonth();

//		dayOfMonth = "0"+dayOfMonth;

//		System.out.println("dayOfMonth::"+dayOfMonth);
//
//		String year = dateString.substring(0, 8);
//		String hour = dateString.substring(10,19);
//
//
//		LocalDateTime localDateTime = parseLocalDateTime(year + dayOfMonth + hour, "yyyy-MM-dd HH:mm:ss");
//
//		System.out.println("localDateTime::"+localDateTime);

//		Runnable runnable = new Runnable() {
//			public void run() {
//				// task to run goes here
//				System.out.println(dateString);
//			}
//		};
//		ScheduledExecutorService service = Executors
//				.newSingleThreadScheduledExecutor();
//		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
//		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
//		oumeione();
	}

//	@Scheduled(cron = "0/3 * * * * *")
//    public static void oumeione() {
//		System.out.println(new Date());//获取当前时间
//
//    }

	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}






}