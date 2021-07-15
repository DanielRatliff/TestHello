import java.util.List;
import java.util.Scanner;

import com.example.exceptions.InvalidCredentialException;
import com.example.models.Post;
import com.example.models.User;
import com.example.services.PostService;
import com.example.services.UserService;

public class SocialHubDriver {
	private static UserService uServ = new UserService("users.txt");
	private static PostService pServ = new PostService("posts.txt");
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		
		User u = null;
		while(!done) {
			if(u==null) {
				System.out.println("Login or Signup? Press 1 to Login, Press 2 to Signup");
				int choice = Integer.parseInt(in.nextLine());
				if(choice==1) {
					System.out.println("Please enter your username: ");
					String username = in.nextLine();
					System.out.println("Please enter password: ");
					String password = in.nextLine();
					try {
						u =uServ.login(username, password);
						System.out.println("Welcome "+u.getFirstname());
					}catch(Exception e) {
						System.out.println("Username or Password was incorrect. Goodbye");
						done = true;
					}
				}else {
					System.out.println("Please enter your first name: ");
					String first = in.nextLine();
					System.out.println("Please enter your last name: ");
					String last = in.nextLine();
					System.out.println("Please enter a password: ");
					String pass = in.nextLine();
					try {
						u=uServ.signUp(first, last, pass);
						System.out.println("You may now log in with the username: "+u.getUsername());
					}catch(Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later.");
						done = true;
					}
				}
			}else {
				System.out.println("To view post press 1, to create post press2");
				int choice = Integer.parseInt(in.nextLine());
				if(choice ==1) {
					List<Post> posts = pServ.getAllPosts();
					for(Post post: posts) {
						System.out.println(post.getUser()+": ");
						System.out.println(post.getContent());
						System.out.println();
					}
					System.out.println("Are you done? 1 yess 2 no");
					choice = Integer.parseInt(in.nextLine());
					done = (choice ==1) ? true : false;
				}else {
					System.out.println("Please enter content");
					String content = in.nextLine();
					Post p = new Post(u.getUsername(),content);
					pServ.addPost(p);
					System.out.println("Post Recieved. Are you done? 1 yess 2 no");
					choice = Integer.parseInt(in.nextLine());
					done = (choice ==1) ? true : false;
				}
			}
		}
		System.out.println("Goodbye!");
		in.close();
	}
}
