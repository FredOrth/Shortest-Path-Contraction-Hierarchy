# Efficient route planning with contraction hierarchies

This project implements a variant of the Contraction Hierarchies algorithm for efficient route planning in large-scale road networks. It uses a dataset mapping the road network of Denmark (569,586 vertices / 1.100.244 edges) as a real-world test case.

The project is structured in two parts:
	•	A Java backend for graph algorithms and preprocessing
	•	A Python pipeline for running experiments and analyzing performance

🔧 This project is still in progress. Additional features, tests, and visualizations are actively being developed.


# Overview

Contraction Hierarchies (CH) are a graph preprocessing technique that enables very fast point-to-point shortest path queries on large-scale road networks. The method was introduced by Geisberger et al. (2008) and is particularly effective in road networks due to their inherent sparsity and hierarchical structure. 

The fundamental goal of Contraction Hierarchies is to shift computational effort to an offline preprocessing phase, enabling substantially faster online query times. This approach aims to balance preprocessing time, additional space usage (due to shortcuts), and query efficiency, making it highly suitable for large, static road networks.

The algorithm consists of two phases:
	1.	Preprocessing (Offline):
 Vertices are contracted one by one in a specific order. During each contraction, shortcut edges are added to preserve shortest path distances through the removed vertex. This       results in an augmented graph with additional edges, but significantly reduced search space during queries.
	•	Time complexity: Roughly \mathcal{O}(n \cdot \log n) to \mathcal{O}(n \cdot \log^2 n), depending on implementation and heuristics
	•	Space complexity: Increases due to shortcut edges; typically a small multiple of the original edge count

 
	2.	Query (Online):
A bidirectional Dijkstra-like search is performed, but only exploring upward edges (i.e., towards higher-ranked nodes). This massively reduces the number of nodes visited compared to standard Dijkstra.
	•	Time complexity: Empirically \mathcal{O}(\log n) or even constant time on real-world road networks
	•	In practice, queries are often 2–3 orders of magnitude faster than basic Dijkstra

This trade-off—heavy preprocessing for ultra-fast queries—makes CH ideal for applications like GPS routing, real-time navigation, and large-scale route planning
