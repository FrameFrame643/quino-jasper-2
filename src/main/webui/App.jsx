import React, { useState } from 'react';
import './style.css';

function App() {
    const [name, setName] = useState('');
    const [people, setPeople] = useState([]);
    const [error, setError] = useState(null);  // Added for error handling

    const handleFetchPeople = async () => {
        try {
            // Replace with your backend URL, if it's different from the frontend
            const response = await fetch('http://localhost:8080/people', {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
            });

            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }

            const data = await response.json();
            setPeople(data);
            setError(null); // Clear previous errors
        } catch (error) {
            console.error('Error fetching people:', error);
            setError('Failed to load people. Please try again later.');
        }
    };

    return (
        <div className="App">
            <h1>Search People</h1>

            <button onClick={handleFetchPeople}>Search</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}  {/* Display error if any */}

            <h2>Results:</h2>
            <ul>
                {people.length === 0 ? (
                    <li>No people found.</li>
                ) : (
                    people.map((person) => (
                        <li key={person.id}>{person.name}</li>
                    ))
                )}
            </ul>
        </div>
    );
}

export default App;
