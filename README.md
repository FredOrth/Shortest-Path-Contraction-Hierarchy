# Efficient route planning with contraction hierarchies

This project implements a variant of the Contraction Hierarchies algorithm for efficient route planning in large-scale road networks. It uses a dataset mapping the road network of Denmark (569,586 vertices / 1.100.244 edges) as a real-world test case.

This project is a collaboration between **Andreas Vinkler**, **Tobias Hansen**, and **Frederik Orth Kølbel** at the **IT University of Copenhagen**.

The project is structured in two parts:
	•	A Java backend for graph algorithms and preprocessing
	•	A Python pipeline for running experiments and analyzing performance

🔧 This project is still in progress. Additional features, tests, and visualizations are actively being developed.


## What Are Contraction Hierarchies?

**Contraction Hierarchies (CH)** are a graph preprocessing technique that enables very fast point-to-point shortest path queries on large-scale road networks. The method was introduced by Geisberger et al. (2008) and is particularly effective due to the hierarchical and sparse nature of road networks.

The fundamental goal of CH is to **shift computational effort to an offline preprocessing phase**, enabling **substantially faster online query times**. This approach balances preprocessing time, additional space usage (from added shortcut edges), and runtime efficiency — making it highly suitable for large, mostly static road networks.

---

### How It Works

The algorithm consists of two main phases:

#### 1. Preprocessing (Offline)

- Vertices are contracted one by one in a specific order.
- For each contracted vertex, **shortcut edges** are added to preserve shortest path distances between its neighbors.
- This results in an **augmented graph** with extra edges but significantly reduced search space for future queries.

**Complexity:**
- **Time:** Approximately `O(n log² n)` when using the edge difference heuristic combined with our custom Dijkstra optimization, which applies early stopping based on nearest-neighbor reachability.
- **Space:** Slightly higher due to added shortcuts; typically a small multiple of the original edge count

#### 2. Query (Online)

- A **bidirectional Dijkstra-like search** is performed, but only using **upward edges** (towards higher-ranked nodes).
- This drastically reduces the number of visited nodes and leads to very fast query times.

**Complexity:**
- **Time:** Empirically around `O(log n)` (or even near-constant time in road networks)
- In practice, **2–3 orders of magnitude faster** than classic Dijkstra

---

This trade-off — **heavier preprocessing for ultra-fast queries** — makes Contraction Hierarchies ideal for applications like **GPS routing**, **real-time navigation**, and **nation-scale route planning**.
