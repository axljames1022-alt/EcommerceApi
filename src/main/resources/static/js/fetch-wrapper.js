async function apiFetch(url, options = {}) {
    const response = await fetch(url, {
        ...options,
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            ...options.headers
        }
    });

    if (response.status === 401) {
        window.location.href = '/login';
        return;
    }

    if (response.status === 403) {
        alert('Access Denied: Wala kang permission sa page na ito');
        return;
    }

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return response.json();
}