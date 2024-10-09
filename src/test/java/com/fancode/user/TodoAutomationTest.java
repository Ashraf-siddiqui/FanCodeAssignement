package com.fancode.user;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TodoAutomationTest {

	private static String baseURI = "http://jsonplaceholder.typicode.com/";

	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = baseURI;
	}

	@Test
	public void getUsers() {

		// Send a GET request to the /users API endpoint and store the response
		Response res = getAPI("/users");

		// Extract user IDs of users from the city 'FanCode' based on latitude and
		// longitude criteria
		List<Integer> userIds = res.jsonPath()
				.getList("findAll { (it.address.geo.lat as Float) >= -40 && (it.address.geo.lat as Float) < 5 && "
						+ "(it.address.geo.lng as Float) >= 5 && (it.address.geo.lng as Float) < 100 }.id");

		// HashMap to store total attempted tasks for each user
		HashMap<Integer, Integer> totalAttemptedTask = new HashMap<>();
		// HashMap to store completed tasks for each user
		HashMap<Integer, Integer> completedAttemptedTask = new HashMap<>();

		// Send a GET request to the /todos API endpoint
		Response res2 = getAPI("/todos");
		// Iterate over each user ID to fetch their todo tasks
		userIds.forEach((userId) -> {
		

			// Count total tasks for the current user and store in the HashMap
			totalAttemptedTask.put(userId, res2.jsonPath().getList("findAll{it.userId ==" + userId + "}.id").size());

			// Count completed tasks for the current user and store in the HashMap
			completedAttemptedTask.put(userId,
					res2.jsonPath().getList("findAll{it.userId ==" + userId + " && it.completed == true}.id").size());

			// Print a blank line for better readability in output
			System.out.println();
		});

		// Print the total attempted tasks for all users for debugging purposes
		System.out.println(totalAttemptedTask);
		// Print the completed tasks for all users for debugging purposes
		System.out.println(completedAttemptedTask);

		// List to hold user IDs of those who have completed more than 50% of their
		// tasks
		List<Integer> usersAboveFiftyPercent = new ArrayList<>();

		// Iterate over each user ID in the totalAttemptedTask HashMap
		for (int userId : totalAttemptedTask.keySet()) {
			// Get the total tasks and completed tasks for the current user
			int totalTasks = totalAttemptedTask.get(userId);
			int completedTask = completedAttemptedTask.get(userId);

			// Calculate the completion percentage
			float completionPercentage = ((float) completedTask / totalTasks) * 100;

			// Check if the completion percentage is greater than 50%
			if (completionPercentage > 50)
				// Add the user ID to the list if the condition is met
				usersAboveFiftyPercent.add(userId);
		}

		// Print the list of user IDs with a completion percentage greater than 50%
		System.out.println("User IDs with completion percentage greater than 50%: " + usersAboveFiftyPercent);
	}

	public Response getAPI(String endPoint) {
		return given().log().all().get(endPoint).andReturn();
	}
}
