# Zeel's Brain v1 - DSA Revision Tracker

**Zeel's Brain v1** is a high-performance web application designed to help developers master Data Structures and Algorithms (DSA) through **Spaced Repetition**. It acts as a digital second brain, ensuring you never forget a problem once you've solved it.

---

## 🚀 The Problem: The Forgetting Curve
Most developers solve a complex DSA problem today but struggle to recall the logic 2 weeks later. Traditional note-taking isn't enough. **Zeel's Brain v1** solves this by scheduling revisions at scientifically optimized intervals.

## ✨ Key Features

### 🧠 Spaced Repetition System (SRS)
The core of the app. When you solve a problem, the system automatically schedules your next revision.
- **Intervals**: 2, 7, 15, 30, 60, 120, and 240 days.
- **Auto-Progression**: Every time you revise, the problem moves to the next interval.
- **Completion**: After 7 successful revisions, the problem is marked as 'Mastered'.

### 📊 Interactive Dashboard
A premium UI built for productivity:
- **Quick Stats**: Track total problems solved, due revisions, and your current streak.
- **Platform Breakdown**: Visual insights into where you solve problems (LeetCode, Codeforces, GFG, etc.).
- **Revision Radar**: Easily see what's due today and what's coming up.

### 🎨 Premium User Experience
- **Glassmorphism Design**: A sleek, modern aesthetic with translucent layers.
- **Micro-animations**: Smooth transitions powered by GSAP and Framer Motion for a fluid feel.
- **Dark Mode Optimized**: Designed to be easy on the eyes during late-night coding sessions.

### 💾 Persistence
- **Local First**: All your data is saved securely in your browser's `LocalStorage`. No database setup required—just open and code.

---

## 🛠 Tech Stack

- **Frontend Framework**: [React 19](https://react.dev/)
- **Build Tool**: [Vite](https://vitejs.dev/)
- **Styling**: Vanilla CSS (Custom tokens and Glassmorphism)
- **Animation**: [GSAP](https://greensock.com/gsap/) & [Framer Motion](https://www.framer.com/motion/)
- **Icons**: [Lucide React](https://lucide.dev/)
- **State Management**: React Context API

---

## 🏗 Project Architecture

The project follows a clean, modular structure:

- `src/context/RevisionContext.jsx`: The central "brain" of the app. Handles the logic for adding questions, marking revisions, and calculating stats.
- `src/pages/`: Contains all main views like `Dashboard`, `Library`, and `TodayRevisions`.
- `src/components/`: Reusable UI elements like `Sidebar`, `Topbar`, and specialized `Charts`.
- `index.css`: The global design system including color palettes and utility classes.

---

## 🏁 How to Run Locally

1. **Clone the repository** (if applicable)
2. **Install dependencies**:
   ```bash
   npm install
   ```
3. **Start the development server**:
   ```bash
   npm run dev
   ```
4. **Open your browser**: Navigate to the URL shown in your terminal (usually `http://localhost:5173`).

---

## 👤 Author
Developed by **Zeel Shah**. Helping developers build a smarter brain for DSA.
