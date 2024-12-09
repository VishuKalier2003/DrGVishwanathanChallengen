// Ques - Twitter Application - https://www.naukri.com/code360/problems/twitter-application_3651392?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Hard&difficulty%5B%5D=Medium&sort_entity=recents&sort_order=DESC&leftPanelTabValue=PROBLEM


import java.util.*;

public class Twitter {
    // Node record to store blocked users and a tweetHeap for tweets
    public record Node(Set<Integer> blocked, PriorityQueue<int[]> tweetHeap) {}

    // TwitterMap record to manage the graph and user data
    public record TwitterMap(Map<Integer, Set<Integer>> graph, Map<Integer, Node> userData) {

    //! Method to create a tweet for a user
    public void createTweet(int user, int tweet, int time) {
        // If user is not in the graph, initialize them
        if(!graph.containsKey(user)) {
            graph.put(user, new HashSet<>(Arrays.asList(user))); // Add self-follow
            userData.put(user, new Node(new HashSet<>(), new PriorityQueue<>((a, b) -> b[0] - a[0]))); // Initialize user data
        }
        // Add tweet to all friends' tweet heaps
        for(int friend : graph.get(user))
            userData.get(friend).tweetHeap.add(new int[]{time, tweet, user});
    }

    //! Method to retrieve the feed of a user
    public String[] getFeed(int user) {
        int tweetCount = 10; // Maximum number of tweets to fetch
        List<String> output = new ArrayList<>(); // To store the result
        Queue<int[]> poppedTweets = new LinkedList<>(); // Temporary queue to store popped tweets
        // Retrieve up to 10 tweets from the tweetHeap
        while(!userData.get(user).tweetHeap.isEmpty() && tweetCount != 0) {
            int tweet[] = userData.get(user).tweetHeap.poll(); // Get the highest-priority tweet
            // Skip blocked tweets
            if(userData.get(user).blocked.contains(tweet[2])) {
                poppedTweets.add(tweet); // Add back to temporary queue
                continue;
            }
            output.add(String.valueOf(tweet[1])); // Add tweet to output
            poppedTweets.add(tweet); // Add back to temporary queue
            tweetCount--; // Decrement tweet count
        }
        // Restore tweets to the original heap
        while(!poppedTweets.isEmpty())
            userData.get(user).tweetHeap.add(poppedTweets.poll());
        // Convert output list to an array and return
        return output.stream().toArray(String[]::new);
    }

    //! Method to allow a user to follow another user
    public void followUser(int follower, int user) {
        graph.putIfAbsent(follower, new HashSet<>(Arrays.asList(follower))); // Initialize follower if needed
        graph.putIfAbsent(user, new HashSet<>(Arrays.asList(user))); // Initialize user if needed
        graph.get(follower).add(user); // Add user to follower's following list
    }

    //! Method to allow a user to unfollow another user
    public void unfollowUser(int unfollower, int user) {
        graph.putIfAbsent(unfollower, new HashSet<>(Arrays.asList(unfollower))); // Initialize unfollower if needed
        graph.putIfAbsent(user, new HashSet<>(Arrays.asList(user))); // Initialize user if needed
        graph.get(unfollower).remove(user); // Remove user from unfollower's following list
        userData.putIfAbsent(unfollower, new Node(new HashSet<>(), new PriorityQueue<>((a, b) -> b[0] - a[0])));
        userData.get(unfollower).blocked.add(user); // Add user to unfollower's blocked list
    }
}

    public static void main(String[] args) {
        // Initialize TwitterMap with empty graph and user data
        TwitterMap twitterMap = new TwitterMap(new HashMap<>(), new HashMap<>());
        input:
        {
            try(Scanner sc = new Scanner(System.in)) {
                int n = sc.nextInt(), time = 0; // Read number of operations and initialize time
                // Process each operation
                for(int i = 0; i < n; i++) {
                    int t = sc.nextInt(); // Read operation type
                    switch(t) {
                        case 1 -> {
                            int user = sc.nextInt(), tweet = sc.nextInt();
                            twitterMap.createTweet(user, tweet, time); // Create tweet
                        }
                        case 2 -> {
                            int user = sc.nextInt();
                            System.out.println(Arrays.toString(twitterMap.getFeed(user))); // Get feed
                        }
                        case 3 -> {
                            int follower = sc.nextInt(), user = sc.nextInt();
                            twitterMap.followUser(follower, user); // Follow user
                        }
                        case 4 -> {
                            int follower = sc.nextInt(), user = sc.nextInt();
                            twitterMap.unfollowUser(follower, user); // Unfollow user
                        }
                        default -> {
                            System.out.println("Invalid Tweet Request !!"); // Invalid operation
                        }
                    }
                }
                sc.close(); // Close the scanner
            }
            break input; // End of input block
        }
    }
}
