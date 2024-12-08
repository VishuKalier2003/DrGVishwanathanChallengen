# ${\color{lightblue} Dr. \space G. \space Vishwanathan \space Challenge}$

The **Dr. G. Vishwanathan Challenge** is a self-driven initiative to push the boundaries of problem-solving and coding skills by tackling some of the most challenging and popular interview questions across top tech companies. This journey is designed to foster consistent learning, enhance DSA proficiency, and build a strong foundation for cracking interviews at leading organizations.

### ${\color{lightblue} Amazon \space Interview \space Questions}$

As part of the Amazon Week in the challenge, the focus is on solving questions inspired by real Amazon interviews. These problems test a candidate's expertise in algorithms, data structures, and problem-solving strategies. Topics often include graphs, dynamic programming, trees, and matrices, which form the core of Amazon's problem-solving assessments. By solving these questions, we not only enhance technical skills but also gain insights into the kind of thinking Amazon values in its engineers.

---

### ${\color{lightblue} Questions \space Table}$

| No. | Question Name | Link | Solution | Submission | Solution Approach |
|-|-|-|-|-|-|
| ***1*** | ${\color{lightgreen} Diameter \space of \space Tree}$ | [Link](https://leetcode.com/problems/diameter-of-binary-tree/description/) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/Diameter.java) | ***07-12-2024*** | To evaluate diameter either use post order DFS with local depth and global diameter or two pass BFS. |
| ***2*** | ${\color{yellow} Best \space Time \space to \space Buy \space and \space Sell \space stock \space II}$ | [Link](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/BuySellStocks.java) | ***07-12-2024*** | We hae three options, to either buy, sell or hold (skip) day, use recursion to find the best option, and perform 2D dp to memonize for larger inputs. |
| ***3*** | ${\color{yellow} Rotting \space Oranges}$ | [Link](https://leetcode.com/problems/rotting-oranges/description/) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/RottingOranges.java) | ***07-12-2024*** | Use BFS to simulate the problem and maintain a fresh orange count, to see after the simulation ends whether the all oranges are rotten or not. |
| ***4*** | ${\color{red} LCA \space of \space Three \space Nodes \space in \space Binary \space Tree}$ | [Link](https://www.naukri.com/code360/problems/lca-of-three-nodes_794944?interviewProblemRedirection=true&category%5B%5D=Data%20Structures&company%5B%5D=Amazon) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/LCAofThreeNodes.java) | ***07-12-2024*** | Develop parent array to mark parent of each child, and a map to store whether a node is ancestor of given three nodes or not, first such node is the LCA. |
| ***5*** | ${\color{red} Burn \space The \space Binary \space Tree}$ | [Link](https://www.naukri.com/code360/problems/time-to-burn-tree_630563?interviewProblemRedirection=true&company%5B%5D=Amazon&sort_entity=recents&sort_order=DESC&count=25&page=8&search=&difficulty%5B%5D=Medium&leftPanelTabValue=PROBLEM&customSource=studio_nav) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/BurnTheTree.java) | ***07-12-2024*** | Develop parent array and perform BFS from the burning node, to check for the left, right and parent nodes which are not burnt. Simulate the problem. |
| ***6*** | ${\color{yellow} Ninja \space and \space Strictly \space Increasing \space Array}$ | [Link](https://www.naukri.com/code360/problems/ninja-and-the-strictly-increasing-array_6946427?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Medium&difficulty%5B%5D=Hard&difficulty%5B%5D=Ninja&sort_entity=recents&sort_order=DESC&leftPanelTabValue=PROBLEM) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/NinjaArray.java) | ***08-12-2024*** | Iterate thorough the array with each index marking as zero, whenever doing make all elements left strictly decreasing, and all right elements as strictly increasing. |
| ***7*** | ${\color{red} Inform \space Employees}$ | [Link](https://www.naukri.com/code360/problems/inform-employees_3738245?interviewProblemRedirection=true&company%5B%5D=Amazon&difficulty%5B%5D=Medium&difficulty%5B%5D=Hard&difficulty%5B%5D=Ninja&sort_entity=recents&sort_order=DESC&leftPanelTabValue=PROBLEM&count=25&page=2&search=&customSource=studio_nav) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/InformEmployees.java) | ***08-12-2024*** | From the given array, first create the graph, then from the given source node find the all nodes shortest path (use Bellman Ford, since the time can be -ve) |
| ***8*** | ${\color{yellow} K\space Distance\space Nodes\space in\space a\space Binary\space Tree}$ | [Link](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/DistanceNodes.java) | ***08-12-2024*** | Develop parent array to mark the parent of each child, and then use it to perfomr bfs till k steps to find all the k-distance nodes. |
| ***9*** | ${\color{red} Find \space Median\space in\space Data\space Stream}$ | [Link](https://leetcode.com/problems/find-median-from-data-stream/description/) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/DataStreamMedian.java) | ***08-12-2024*** | To split the array everytime and evaluate, use two heaps, min heap and max heap to store the last element of first half, and first element of second half |
| ***10*** | ${\color{red} Critical \space Connections \space in \space Network}$ | [Link](https://leetcode.com/problems/critical-connections-in-a-network/description/) | [Solution](https://github.com/VishuKalier2003/DrGVishwanathanChallengen/blob/main/Amazon/CriticalConnection.java) | ***08-12-2024*** | To find the critical edges and articulation points in a graph, we use a developed algorithm to find them either Kosaraju's or Tarjan's |

---

### ${\color{lightblue}How \space to \space Use \space This \space Repository}$

1. **Navigate to Problems:** Use the "Link" column to access the problem statement directly on the respective platform.
2. **Explore Solutions:** Visit the "Solution" column to find detailed explanations and implementations of the problems.
3. **Understand Approaches:** Refer to the "Approaches Used" column to see the techniques applied in solving the problem.
4. **Learn Basic Concepts:** Each question includes a brief description to help understand its core objective.

---

### ${\color{lightblue}Credits}$

This challenge is inspired by ***VIT Bhopal*** and ***Sriram R*** Sir's vision for continuous learning and excellence.

### ${\color{lightblue}Contributor}$

- ***Vishu Kalier***

